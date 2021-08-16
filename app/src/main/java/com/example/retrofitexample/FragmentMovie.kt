package com.example.retrofitexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofitexample.databinding.MovieFragmentBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class FragmentMovie : Fragment(R.layout.movie_fragment) {

    private lateinit var binding: MovieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = MovieFragmentBinding.bind(view!!)
        return view
    }

    private val simpleMovie = """
        {
        "imdb_id":"123"
        "title":"Title",
        "year":"2014",
        }        
    """.trimIndent()
    private val simpleMovieList = """
        [
        {
        "imdb_id":"123"
        "title":"Title",
        "rating":"PG",
        "year":"2014",
        },
        {
        "imdb_id":"1234"
        "title":"Titlewe",
        "rating":"G",
        "year":"2013",
        }
        ]        
    """.trimIndent()

    override fun onResume() {
        super.onResume()
        convertSimpleMovieJsonToInstance()
    }

    private fun convertSimpleMovieJsonToInstance() {
        val moshi = Moshi.Builder().build()

        val adapter = moshi.adapter(Movie::class.java)
        binding.apply {
            try {
                val movie = adapter.fromJson(simpleMovie)
                txtMain.text = movie.toString()
            } catch (e: Exception) {
                txtMain.text = "parse error ${e.message}"
            }
        }
    }

    private fun convertMovieListJsonToInstance() {
        val moshi = Moshi.Builder().build()

        val movieListType = Types.newParameterizedType(
            List::class.java,
            Movie::class.java
        )
        val adapter = moshi.adapter<List<Movie>>(movieListType)

        try {
            val movies = adapter.fromJson(simpleMovieList)
            binding.txtMain.text = movies.toString()
        } catch (e: Exception) {
            binding.txtMain.text = "e ${e.message}"
        }
    }
}